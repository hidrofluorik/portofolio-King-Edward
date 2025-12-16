/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class favorite {
    private String id_fav;
    private String id_song;
    private String nickname;

    public favorite() {}

    public favorite(String id_fav, String id_song, String nickname) {
        this.id_fav = id_fav;
        this.id_song = id_song;
        this.nickname = nickname;
    }

    
    public String getId_fav() {
        return id_fav;
    }

    public void setId_fav(String id_fav) {
        this.id_fav = id_fav;
    }

    

    public String getId_song() {
        return id_song;
    }

    public void setId_song(String id_song) {
        this.id_song = id_song;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    

    
}

