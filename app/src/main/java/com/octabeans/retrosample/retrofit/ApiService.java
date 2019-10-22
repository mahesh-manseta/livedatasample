package com.octabeans.retrosample.retrofit;

import com.octabeans.retrosample.data.ResNote;
import com.octabeans.retrosample.data.ResVendor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    // Register new user
    @FormUrlEncoded
    @POST("notes/user/register")
    Call<User> register(@Field("device_id") String deviceId);

    // Create note
    @FormUrlEncoded
    @POST("notes/new")
    Call<ResNote> createNote(@Field("note") String note);

    // Fetch all notes
    @GET("get_all_vendors_open")
    Call<List<ResNote>> fetchAllNotes();

    // Fetch all notes
    @POST("get_all_vendors_open")
    Call<ResVendor> fetchAllVendors();

    // Update single note
    @FormUrlEncoded
    @PUT("notes/{id}")
    void updateNote(@Path("id") int noteId, @Field("note") String note);

    // Delete note
    @DELETE("notes/{id}")
    void deleteNote(@Path("id") int noteId);

}
