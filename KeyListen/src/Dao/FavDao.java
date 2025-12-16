package dao;

import model.favorite;
import model.song;
import config.koneksiKey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavDao {

    private koneksiKey k = new koneksiKey();

    // ================= GET ALL FAVORITE =================
    public List<favorite> getAll() {
        List<favorite> list = new ArrayList<>();
        String sql = "SELECT * FROM favorite";

        try (Connection con = k.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                favorite f = new favorite(
                    rs.getString("id_fav"),
                    rs.getString("id_song"),
                    rs.getString("nickname")
                );
                list.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= INSERT FAVORITE =================
    public boolean insert(favorite f) {
        String sql = "INSERT INTO favorite (id_fav, id_song, nickname) VALUES (?, ?, ?)";

        try (Connection con = k.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, f.getId_fav());
            ps.setString(2, f.getId_song());
            ps.setString(3, f.getNickname());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE FAVORITE =================
    public boolean delete(String idFav) {
        String sql = "DELETE FROM favorite WHERE id_fav = ?";

        try (Connection con = k.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idFav);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= FAVORITE BY NICKNAME =================
    public List<song> getFavoriteSongsByNickname(String nickname) {
        List<song> list = new ArrayList<>();

        String sql = """
            SELECT s.id_song, s.title, s.artist, s.filePath, s.duration
            FROM favorite f
            JOIN song s ON f.id_song = s.id_song
            WHERE f.nickname = ?
        """;

        try (Connection con = k.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nickname);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                song s = new song(
                    rs.getString("id_song"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("filePath"),
                    rs.getInt("duration")
                );
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
