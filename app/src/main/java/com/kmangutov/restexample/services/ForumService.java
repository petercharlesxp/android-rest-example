package com.kmangutov.restexample.services;

import com.kmangutov.restexample.models.Comment;
import com.kmangutov.restexample.models.Post;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by kmangutov on 3/25/15.
 */
public class ForumService {

    private static final String FORUM_SERVER_URL = "http://192.168.43.131"; //"http://jsonplaceholder.typicode.com";
    private ForumApi mForumApi;

    public ForumService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORUM_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mForumApi = restAdapter.create(ForumApi.class);
    }

    public ForumApi getApi() {

        return mForumApi;
    }

    public interface ForumApi {

        @GET("/PDO_Connecting_to_a_MySQL_Database_using_the_PDO_Library_in_PHP")
        //@GET("/posts")
        public Observable<List<Post>>
            getPosts();

        @GET("/posts/{id}")
        public Observable<Post>
            getPost(@Path("id") int postId);

        @GET("/comments")
        public Observable<List<Comment>>
            getComments(@Query("postId") int postId);

        @POST("/posts")
        public Observable<Post>
            postPost(Post post);
    }
}
