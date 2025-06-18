package com.simplecity.amp_library.model;Add commentMore actions



import java.util.ArrayList;


import java.util.Collections;


import java.util.List;



public class Suggestion {




    private final AlbumArtist mostPlayedArtist;


    private final Album mostPlayedAlbum;


    private final Song mostPlayedSong;


    private final List<Song> favouriteSongsOne;


    private final List<Song> favouriteSongsTwo;


    private final List<Album> recentlyPlayedAlbums;


    private final List<Album> recentlyAddedAlbumsOne;


    private final List<Album> recentlyAddedAlbumsTwo;




    /**


     * @param mostPlayedArtist       the top artist


     * @param mostPlayedAlbum        the top album


     * @param mostPlayedSong         the top song


     * @param favouriteSongsOne      first set of favorite songs


     * @param favouriteSongsTwo      second set of favorite songs


     * @param recentlyPlayedAlbums   recently played albums


     * @param recentlyAddedAlbumsOne first batch of recently added albums


     * @param recentlyAddedAlbumsTwo second batch of recently added albums


     */


    public Suggestion(


            AlbumArtist mostPlayedArtist,

            Album mostPlayedAlbum,

            Song mostPlayedSong,


            List<Song> favouriteSongsOne,


            List<Song> favouriteSongsTwo,


            List<Album> recentlyPlayedAlbums,


            List<Album> recentlyAddedAlbumsOne,


            List<Album> recentlyAddedAlbumsTwo


    ) {


        this.mostPlayedArtist = mostPlayedArtist;

        this.mostPlayedAlbum = mostPlayedAlbum;

        this.mostPlayedSong = mostPlayedSong;


        this.favouriteSongsOne = new ArrayList<>(favouriteSongsOne);


        this.favouriteSongsTwo = new ArrayList<>(favouriteSongsTwo);


        this.recentlyPlayedAlbums = new ArrayList<>(recentlyPlayedAlbums);


        this.recentlyAddedAlbumsOne = new ArrayList<>(recentlyAddedAlbumsOne);


        this.recentlyAddedAlbumsTwo = new ArrayList<>(recentlyAddedAlbumsTwo);


    }





    public AlbumArtist getMostPlayedArtist() {


        return mostPlayedArtist;


    }





    public Album getMostPlayedAlbum() {


        return mostPlayedAlbum;


    }





    public Song getMostPlayedSong() {


        return mostPlayedSong;


    }





    public List<Song> getFavouriteSongsOne() {


        return Collections.unmodifiableList(favouriteSongsOne);


    }





    public List<Song> getFavouriteSongsTwo() {


        return Collections.unmodifiableList(favouriteSongsTwo);


    }





    public List<Album> getRecentlyPlayedAlbums() {


        return Collections.unmodifiableList(recentlyPlayedAlbums);


    }





    public List<Album> getRecentlyAddedAlbumsOne() {


        return Collections.unmodifiableList(recentlyAddedAlbumsOne);


    }





    public List<Album> getRecentlyAddedAlbumsTwo() {


        return Collections.unmodifiableList(recentlyAddedAlbumsTwo);

    }



    @Override

    public String toString() {

        return "Suggestion{" +


               "mostPlayedArtist=" + mostPlayedArtist +


               ", mostPlayedAlbum=" + mostPlayedAlbum +


               ", mostPlayedSong=" + mostPlayedSong +


               ", favouriteSongsOne=" + favouriteSongsOne +


               ", favouriteSongsTwo=" + favouriteSongsTwo +


               ", recentlyPlayedAlbums=" + recentlyPlayedAlbums +


               ", recentlyAddedAlbumsOne=" + recentlyAddedAlbumsOne +


               ", recentlyAddedAlbumsTwo=" + recentlyAddedAlbumsTwo +


               '}';

    }

}