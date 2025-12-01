//package playlist;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class PlayList extends JFrame implements ActionListener {
//    private JTextField title, artist, album, year, search;
//    private JButton btnAdd, btnDelete, btnSearch, btnShowAll;
//    private JTable musicTable;
//    private DefaultTableModel musicTableModel;
//    private ArrayList<Music> musicList = new ArrayList<>();
//
//    public PlayList() throws HeadlessException {
//        setTitle("PlayList");
//        setSize(800, 800);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // 패널 구성
//        JPanel topPanel = new JPanel(new GridLayout(0, 1));
//
//        // 입력 패널
//        JPanel inputPanel = new JPanel();
//        title = new JTextField(10);
//        artist = new JTextField(10);
//        album = new JTextField(10);
//        year = new JTextField(10);
//        btnAdd = new JButton("등록");
//
//        inputPanel.add(new JLabel("제목"));
//        inputPanel.add(title);
//        inputPanel.add(new JLabel("아티스트"));
//        inputPanel.add(artist);
//        inputPanel.add(new JLabel("앨범"));
//        inputPanel.add(album);
//        inputPanel.add(new JLabel("발매일  "));
//        inputPanel.add(year);
//        inputPanel.add(btnAdd);
//
//        // 검색 패널
//        JPanel searchPanel = new JPanel();
//        search = new JTextField(15);
//        btnSearch = new JButton("검색");
//        btnDelete = new JButton("선택 삭제");
//        btnShowAll = new JButton("전체 보기");
//        searchPanel.add(new JLabel("음악 검색"));
//        searchPanel.add(search);
//        searchPanel.add(btnSearch);
//        searchPanel.add(btnShowAll);
//
//        // 테이블 구성
//        String[] columnNames = {"제목", "아티스트", "앨범", "발매일"};
//        musicTableModel = new DefaultTableModel(columnNames, 0);
//        musicTable = new JTable(musicTableModel);
//        musicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        JScrollPane scrollPane = new JScrollPane(musicTable);
//
//        // 삭제 버튼
//        btnDelete = new JButton("선택 삭제");
//
//        // 패널 배치
//        topPanel.add(inputPanel);
//        topPanel.add(searchPanel);
//        add(topPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//        add(btnDelete, BorderLayout.SOUTH);
//
//        // 등록 기능 (중복 검사 포함)
//        btnAdd.addActionListener(e -> {
//            String title_ = title.getText().trim();
//            String artist_ = artist.getText().trim();
//            String album_ = album.getText().trim();
//            String year_ = year.getText().trim();
//
//            if (title_.isEmpty() || artist_.isEmpty() || album_.isEmpty() || year_.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "모든 값을 입력해 주세요.");
//                return;
//            }
//
//            for (Music m : musicList) {
//                if (m.getTitle().equals(title_) &&
//                        m.getArtist().equals(artist_) &&
//                        m.getAlbum().equals(album_) &&
//                        m.getYear().equals(year_)) {
//                    JOptionPane.showMessageDialog(this, "이미 등록된 음악입니다.");
//                    return;
//                }
//            }
//
//            Music music = new Music(title_, artist_, album_, year_);
//            musicList.add(music);
//
//            musicTableModel.addRow(new Object[]{
//                    music.getTitle(),
//                    music.getArtist(),
//                    music.getAlbum(),
//                    music.getYear()
//            });
//        });
//
//        // 검색 기능
//        btnSearch.addActionListener(e -> {
//            String searchKeyword = search.getText().trim().toLowerCase();
//            musicTableModel.setRowCount(0); // 테이블 초기화
//
//            for (Music music : musicList) {
//                if (music.getTitle().toLowerCase().contains(searchKeyword) ||
//                        music.getArtist().toLowerCase().contains(searchKeyword) ||
//                        music.getAlbum().toLowerCase().contains(searchKeyword)) {
//                    musicTableModel.addRow(new Object[]{
//                            music.getTitle(),
//                            music.getArtist(),
//                            music.getAlbum(),
//                            music.getYear()
//                    });
//                }
//            }
//        });
//
//        // 전체 보기 기능
//        btnShowAll.addActionListener(e -> {
//            musicTableModel.setRowCount(0);
//            for (Music music : musicList) {
//                musicTableModel.addRow(new Object[]{
//                        music.getTitle(),
//                        music.getArtist(),
//                        music.getAlbum(),
//                        music.getYear()
//                });
//            }
//        });
//
//        // 삭제 버튼 연결
//        btnDelete.addActionListener(this);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int row = musicTable.getSelectedRow();
//
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요.");
//            return;
//        }
//
//        int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            // musicList와 테이블의 인덱스를 동기화하여 삭제
//            musicList.remove(row);
//            musicTableModel.removeRow(row);
//        }
//    }
//
//    public static void main(String[] args) {
//        new PlayList();
//    }
//}





