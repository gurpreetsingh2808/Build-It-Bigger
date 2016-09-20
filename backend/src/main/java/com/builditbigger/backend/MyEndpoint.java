/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.builditbigger.backend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.builditbigger.com",
    ownerName = "backend.builditbigger.com",
    packagePath=""
  )
)
public class MyEndpoint {

    List<MyBean> beanList = new ArrayList<>();

    /** A simple endpoint method that takes a name and says Hi back */
    /*
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
*/
    @ApiMethod(name = "getJoke")
    public List<MyBean> getJoke() {
        //MyBean response = new MyBean();
        //response.setData(new Jokes().tellJoke());
        List<String> listAllJokes = Jokes.getJokes();
        for (String joke : listAllJokes) {
            beanList.add(new MyBean(joke));
        }
        return beanList;
    }

}
