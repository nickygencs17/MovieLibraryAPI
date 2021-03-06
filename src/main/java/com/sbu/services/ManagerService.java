package com.sbu.services;

import com.sbu.controller.ManagerController;
import com.sbu.data.ActorRepository;
import com.sbu.data.AppearedInRepository;
import com.sbu.data.LocationRepository;
import com.sbu.data.MovieRepository;
import com.sbu.data.entitys.*;
import com.sbu.exceptions.BadRequestException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

import static com.sbu.services.ResponseUtil.build200;
import static com.sbu.services.ResponseUtil.build201;

/**
 * Created by nicholasgenco on 4/10/17.
 * Not implmented:
 * Manger
 Edit movies
 Edit information for an employee

 *
 */
@CrossOrigin
@RestController
@RequestMapping("/storage/manager")
public class ManagerService extends StorageService {


    @Autowired
    private ManagerController managerController;

    @Autowired
    private final InMemoryUserDetailsManager userManager;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private AppearedInRepository appearedInRepository;


    private static final GrantedAuthority ROLE_EMPLOYEE = new SimpleGrantedAuthority("ROLE_EMPLOYEE");


    public ManagerService(InMemoryUserDetailsManager userManager) {
        this.userManager = userManager;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    public Response addEmployee(@RequestBody @Valid Employee employee) throws IOException, ParseException, BadRequestException {

        if(!locationRepository.exists(employee.getEmployee().getLocation().getzipcode())){
            locationRepository.save(employee.getEmployee().getLocation());
        }
        employee.setSsn(employee.getId());
        managerController.createEmployee(employee);

       if(userManager.userExists(employee.getId().toString())){
         throw new BadRequestException("SSN already exists");
        }


        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(ROLE_EMPLOYEE);
        userManager.createUser(new User(employee.getSsn().toString(), employee.getEmployee().getPassword(), roles));

        return build201("Created");
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/actor",method = RequestMethod.POST)
    public Response addActor(@RequestBody @Valid Actor actor, @RequestParam("movieIDCSV") String movieIDCSV) throws IOException, ParseException, BadRequestException {
        Long actorNumber = actorRepository.count();
        actorNumber++;
        actor.setId(Integer.parseInt(actorNumber.toString()));
        actorRepository.save(actor);

        List<String> movieIDs = Arrays.asList(movieIDCSV.split("\\s*,\\s*"));
        Set<Movie> movies = new HashSet<>();

        for (String movieId: movieIDs){
            movies.add(movieRepository.findOne(Integer.parseInt(movieId)));

        }
        for( Movie m: movies){
            if(m!=null)
            appearedInRepository.save(new AppearedIn(actor.getId(),m));
        }

        return build201("Created");
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movie",method = RequestMethod.POST)
    public Response addMovie(@RequestBody @Valid Movie movie, @RequestParam(name ="actorIDCSV", required = false) String actorIDCSV ) throws IOException, ParseException, BadRequestException {
        Long movieNumber = movieRepository.count();
        if(movie.getRating()<0||movie.getRating()>5){
            throw new BadRequestException();
        }
        try{
            Integer.parseInt(movie.getDistrfee());
        }
        catch(Exception e){
            throw new BadRequestException();
        }


        movie.setID(movieNumber.intValue()+1);
        managerController.createMovie(movie);

        if(actorIDCSV !=null){

            List<String> actorIDs = Arrays.asList(actorIDCSV .split("\\s*,\\s*"));
            Set<Actor> actors = new HashSet<>();

            for (String actorId: actorIDs){
                actors.add(actorRepository.findOne(Long.parseLong(actorId)));

            }
            for( Actor a: actors){
                if(a!=null)
                    appearedInRepository.save(new AppearedIn(a.getId(),movie));
            }
        }

        return build201("Created");
    }





    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movie/{movieID}", method= RequestMethod.GET)
    public Response getMovie(@PathVariable("movieID") String movieID) throws IOException {
        Movie movie= managerController.getMovieById(Integer.valueOf(movieID));
        return build200(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movie/{movieID}", method= RequestMethod.DELETE)
    public Response deleteMovie(@PathVariable("movieID") String movieID) throws IOException {
        managerController.deleteMovie(Integer.valueOf(movieID));
        return build200("Delete Okay");
    }




    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{employeeID}", method= RequestMethod.GET)
    public Response getEmployee(@PathVariable("employeeID") String employeeID) throws IOException {
        Employee employee= managerController.getEmployee(Long.valueOf(employeeID));
        return build200(employee);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{employeeID}", method= RequestMethod.DELETE)
    public Response deleteEmployee(@PathVariable("employeeID") String employeeID) throws IOException {
        managerController.deleteEmployee(Long.valueOf(employeeID));
        return build200("Delete Okay");
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sales/{month}",method= RequestMethod.GET)
    public Response getSalesReport(@PathVariable("month") int month) throws IOException {
        int salesReport = managerController.getSalesReport(month);
        return build200(salesReport);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies",method= RequestMethod.GET)
    public Response getAllMovies() throws IOException {
        Iterable<Movie> movies= managerController.getMovies();
        return build200(movies);
    }




    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/type/{movieType}",method= RequestMethod.GET)
    public Response getMoviesByMovieType(@PathVariable("movieType") String movieType) throws IOException {
        Iterable<Movie> movies= managerController.getMoviesByMovieType(movieType);


        return build200(movies);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/name/{movieName}",method= RequestMethod.GET)
    public Response getMoviesByMovieName(@PathVariable("movieName") String movieName) throws IOException {
        Movie info = managerController.getMoviesByMovieName(movieName);
        return build200(info);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/customer",method= RequestMethod.GET)
    public Response getMoviesByCustomerName(@RequestParam(value = "firstname", required = true) String firstname,
                                            @RequestParam(value = "lastname", required = true) String lastname) throws IOException {
        Set<Movie> movies =managerController.getMoviesByCustomerName(firstname,lastname);

        return build200(movies);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/mostTransactions", method = RequestMethod.GET)
    public Response getCustomerWithMostTransactions () {
        Set<Customer> info = managerController.getCustomerWithMostTransactions();
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/employee",  method = RequestMethod.GET)
    public Response getEmployeeWithMostTransaction(){
        Employee info = managerController.getEmployeeWithMostTransaction();
        return build200(info);
    }



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/mostRented",  method = RequestMethod.GET)
    public Response getMostRentedMovie() {
        Iterable<Movie> info = managerController.getMostRentedMovies();
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/editEmployee", method = RequestMethod.PUT)
    public Response editEmployee(@RequestBody Employee employee) throws Exception {
        managerController.editEmployee(employee);
        return build200("Edit Okay");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/editMovies", method = RequestMethod.PUT)
    public Response editMovie(@RequestBody Movie movie) throws Exception {

        managerController.editMovie(movie);
        return build200("Edit Okay");
    }



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/dump", method = RequestMethod.GET)
    public Response dumpData() throws Exception {
    String dump = "mysqldump -unewuser -ppassword nicksdb > file.sql";
    String[] cmdarray = {"/bin/sh","-c", dump};
    Process p = Runtime.getRuntime().exec(cmdarray);
    if (p.waitFor() != 0) {
      throw new RuntimeException();
    }
        return build201("OK Created");
    }



}
