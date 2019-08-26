package com.example.mizansen.Network.ModelNetwork;

import java.util.ArrayList;
import java.util.List;

public class MoviesModel extends ErrorModel {

    public int id;
    public String title,descripton,image;
    public List<CategoryModel> categories = new ArrayList<>();
    public List<StreamLinksModel> stream_links = new ArrayList<>();

}
