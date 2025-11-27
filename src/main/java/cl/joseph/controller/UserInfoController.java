package cl.joseph.controller;

import cl.joseph.constants.Constants;
import cl.joseph.model.dto.UserInfoDto;
import cl.joseph.service.UserInfoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class UserInfoController {

    @Inject
    private UserInfoService crudService;

    @GET
    @Path("/get-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(crudService.getUsers()).build();
    }


    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserInfoDto userInfoDto) {

        return Response.ok(crudService.createUser(userInfoDto)).build();

    }

    @PUT
    @Path("/update-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserInfoDto userInfoDto) {
        return Response.ok(crudService.updateUser(userInfoDto)).build();
    }

    @DELETE
    @Path("/delete-user/{user_email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam(Constants.PATH_PARAM_USER_EMAIL) String userEmail) {
        crudService.deleteUser(userEmail);
        return Response.noContent().build();
    }
}
