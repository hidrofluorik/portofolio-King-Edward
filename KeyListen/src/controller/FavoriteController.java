package controller;

import dao.FavDao;
import model.favorite;
import model.song;
import view.favView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FavoriteController {

    private favView view;
    private FavDao dao;
    private DefaultTableModel tableModel;

    public FavoriteController(favView view) {
        this.view = view;
        this.dao = new FavDao();
        initTable();
        setButtonListener();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(
            new Object[]{"ID Favorite", "ID Song", "Nickname"}, 0
        );
        view.getTabel_fav().setModel(tableModel);
    }

    // ================= TAMPIl FAVORIT =================
    public void tampilFavoritByNickname() {
        String nickname = view.getTxt_nickname().getText();

        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Masukkan nickname dulu!");
            return;
        }

        List<song> list = dao.getFavoriteSongsByNickname(nickname);
        tableModel.setRowCount(0);

        for (song s : list) {
            tableModel.addRow(new Object[]{
                s.getId_song(),
                s.getTitle(),
                s.getArtist(),
                s.getFilePath(),
                s.getDuration()
            });
        }
    }

    // ================= ADD FAVORITE =================
    public void simpandata() {
        favorite f = new favorite();
        f.setId_fav(view.getTxtid_fav().getText());
        f.setId_song(view.getTxtid_song().getText());
        f.setNickname(view.getTxt_nickname().getText());

        if (dao.insert(f)) {
            JOptionPane.showMessageDialog(view, "Favorit ditambahkan");
            tampilFavoritByNickname();
            clearform();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan favorit");
        }
    }

    // ================= DELETE FAVORITE =================
    public void delete() {
        int row = view.getTabel_fav().getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data dulu!");
            return;
        }

        String idFav = view.getTxtid_fav().getText();

        if (dao.delete(idFav)) {
            JOptionPane.showMessageDialog(view, "Favorit dihapus");
            tampilFavoritByNickname();
            clearform();
        } else {
            JOptionPane.showMessageDialog(view, "Gagal menghapus favorit");
        }
    }

    private void clearform() {
        view.getTxtid_fav().setText("");
        view.getTxtid_song().setText("");
    }

    private void setButtonListener() {
        view.getBtn_addfav().addActionListener(e -> simpandata());
        view.getBtnDelete().addActionListener(e -> delete());
    }
}
