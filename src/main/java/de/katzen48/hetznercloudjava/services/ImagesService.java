package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.images.GetImageResponse;
import de.katzen48.hetznercloudjava.reponses.images.GetImagesResponse;
import de.katzen48.hetznercloudjava.resources.Image.Type;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImagesService 
{
	@Headers({ "Accept: application/json" })
	@GET("images")
	public Call<GetImagesResponse> getImages();
	
	@Headers({ "Accept: application/json" })
	@GET("images?sort={sort}")
	public Call<GetImagesResponse> getSortedImages(@Path("sort") String sorting);
	
	@Headers({ "Accept: application/json" })
	@GET("images?type={type}")
	public Call<GetImagesResponse> getImagesByType(@Path("type") Type type);
	
	@Headers({ "Accept: application/json" })
	@GET("images?bound_to={bound_to}")
	public Call<GetImagesResponse> getImagesBoundTo(@Path("bound_to") String boundTo);
	
	@Headers({ "Accept: application/json" })
	@GET("images?name={name}")
	public Call<GetImagesResponse> getImagesByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("images?sort={sort}&type={type}")
	public Call<GetImagesResponse> getSortedImagesByType(@Path("sort") String sorting, @Path("type") String type);
	
	@Headers({ "Accept: application/json" })
	@GET("images?sort={sort}&bound_to={bound_to}")
	public Call<GetImagesResponse> getSortedImagesBoundTo(@Path("sort") String sorting, @Path("bound_to") String boundTo);
	
	@Headers({ "Accept: application/json" })
	@GET("images?sort={sort}&name={name}")
	public Call<GetImagesResponse> getSortedImagesByName(@Path("sort") String sorting, @Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("images?type={type}&bound_to={bound_to}")
	public Call<GetImagesResponse> getImagesByTypeBoundTo(@Path("type") String type, @Path("bound_to") String boundTo);
	
	@Headers({ "Accept: application/json" })
	@GET("images?type={type}&name={name}")
	public Call<GetImagesResponse> getImagesByTypeAndName(@Path("type") String type, @Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("images?sort={sort}&type={type}&bound_to={bound_to}&name={name}")
	public Call<GetImagesResponse> getSortedImagesByTypeAndNameBoundTo(@Path("sort") String sorting, @Path("type") String type, @Path("bound_to") String boundTo, @Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("images/{id}")
	public Call<GetImageResponse> getImage(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@PUT("images/{id}")
	public Call<GetImageResponse> updateImage(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@PUT("images/{id}")
	public Call<GetImageResponse> updateImageDescription(@Path("id") int id, @Query("description") String description);
	
	@Headers({ "Accept: application/json" })
	@PUT("images/{id}")
	public Call<GetImageResponse> updateImageType(@Path("id") int id, @Query("type") Type type);
	
	@Headers({ "Accept: application/json" })
	@PUT("images/{id}")
	public Call<GetImageResponse> updateImage(@Path("id") int id, @Query("description") String description, @Query("type") Type type);
	
	@Headers({ "Accept: application/json" })
	@DELETE("images/{id}")
	public Call<Object> delete(@Path("id") int id);
}
