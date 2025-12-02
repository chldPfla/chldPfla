//package playlist;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class PlayList01 extends JFrame implements ActionListener {
//    private JTextField title, artist, album, year, search;
//    private JButton btnAdd, btnDelete, btnSearch,
//            btnPlay, btnNext, btnPrevious;
//    private JTable musicTable;
//    private DefaultTableModel musicTableModel;
//    private ArrayList<Music> musicList = new ArrayList<>();
//    private int currentIndex = -1;
//
//    public PlayList01() throws HeadlessException {
//        setTitle("PlayList");
//        setSize(800, 800);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
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
//
//        inputPanel.add(new JLabel("제목"));
//        inputPanel.add(title);
//        inputPanel.add(new JLabel("아티스트"));
//        inputPanel.add(artist);
//        inputPanel.add(new JLabel("앨범"));
//        inputPanel.add(album);
//        inputPanel.add(new JLabel("발매일"));
//        inputPanel.add(year);
//        inputPanel.add(btnAdd);
//
//
//
//        // 검색 패널
//        JPanel searchPanel = new JPanel();
//        search = new JTextField(15);
//        btnSearch = new JButton("\uD83D\uDD0D");
//        btnDelete = new JButton("선택 삭제");
//
//        searchPanel.add(new JLabel("음악 검색"));
//        searchPanel.add(search);
//        searchPanel.add(btnSearch);
//        searchPanel.add(btnDelete);
//
//
//        // 테이블
//        String[] columnNames = {"제목", "아티스트", "앨범", "발매일", "즐겨찾기"};
//        musicTableModel = new DefaultTableModel(columnNames, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return column == 4;
//            }
//        };
//        musicTable = new JTable(musicTableModel);
//        musicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        JScrollPane scrollPane = new JScrollPane(musicTable);
//
//        // 삭제 버튼
//        //btnDelete = new JButton("선택 삭제");
//
//        // 재생 컨트롤
//        btnPlay = new JButton("▶");
//        btnPrevious = new JButton("⏮");
//        btnNext = new JButton("⏭");
//
//        JPanel controlPanel = new JPanel();
//        controlPanel.add(btnPrevious);
//        controlPanel.add(btnPlay);
//        controlPanel.add(btnNext);
//
//        topPanel.add(inputPanel);
//        topPanel.add(searchPanel);
//        add(topPanel, BorderLayout.NORTH);
//        add(scrollPane, BorderLayout.CENTER);
//        JPanel bottomPanel = new JPanel(new BorderLayout());
//        //bottomPanel.add(btnDelete, BorderLayout.EAST);
//        bottomPanel.add(controlPanel, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.SOUTH);
//
//        // 즐겨찾기 칼럼 폭
//        musicTable.getColumnModel().getColumn(4).setMaxWidth(55);
//        musicTable.getColumnModel().getColumn(4).setMinWidth(45);
//        musicTable.getColumnModel().getColumn(4).setPreferredWidth(52);
//
//        // 등록 버튼
//        btnAdd.addActionListener(e -> addMusic());
//
//        // 검색 버튼
//        btnSearch.addActionListener(e -> searchMusic());
//
//        // 즐겨찾기 클릭
//        musicTable.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent e) {
//                int row = musicTable.getSelectedRow();
//                int col = musicTable.getSelectedColumn();
//                if (col == 4 && row != -1) {
//                    toggleFavorite(row);
//                }
//            }
//        });
//
//        // 재생 버튼
//        btnPlay.addActionListener(e -> playSelected());
//        btnPrevious.addActionListener(e -> playPrevious());
//        btnNext.addActionListener(e -> playNext());
//
//        // 삭제 버튼
//        btnDelete.addActionListener(this);
//
//        setVisible(true);
//    }
//
//    // 음악 등록
//    private void addMusic() {
//        String title_ = title.getText().trim();
//        String artist_ = artist.getText().trim();
//        String album_ = album.getText().trim();
//        String year_ = year.getText().trim();
//
//        if (title_.isEmpty() || artist_.isEmpty() || album_.isEmpty() || year_.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "모든 값을 입력해 주세요.");
//            return;
//        }
//
//        // 중복 체크
//        for (Music m : musicList) {
//            if (m.getTitle().equalsIgnoreCase(title_) &&
//                    m.getArtist().equalsIgnoreCase(artist_) &&
//                    m.getAlbum().equalsIgnoreCase(album_) &&
//                    m.getYear().equalsIgnoreCase(year_)) {
//                JOptionPane.showMessageDialog(this, "이미 등록된 음악입니다.");
//                return;
//            }
//        }
//
//        Music music = new Music(title_, artist_, album_, year_);
//        musicList.add(music);
//
//        musicTableModel.addRow(new Object[]{
//                music.getTitle(),
//                music.getArtist(),
//                music.getAlbum(),
//                music.getYear(),
//                "🤍"
//        });
//
//        title.setText("");
//        artist.setText("");
//        album.setText("");
//        year.setText("");
//    }
//
//    // 검색 기능
//    private void searchMusic() {
//        String keyword = search.getText().trim().toLowerCase();
//        musicTableModel.setRowCount(0);
//
//        for (Music m : musicList) {
//            if (m.getTitle().toLowerCase().contains(keyword) ||
//                    m.getArtist().toLowerCase().contains(keyword) ||
//                    m.getAlbum().toLowerCase().contains(keyword)) {
//                musicTableModel.addRow(new Object[]{
//                        m.getTitle(),
//                        m.getArtist(),
//                        m.getAlbum(),
//                        m.getYear(),
//                        m.isFavorite() ? "❤️" : "🤍"
//                });
//            }
//        }
//    }
//
//    // 즐겨찾기 토글
//    private void toggleFavorite(int row) {
//        String titleVal = (String) musicTableModel.getValueAt(row, 0);
//        String artistVal = (String) musicTableModel.getValueAt(row, 1);
//        String albumVal = (String) musicTableModel.getValueAt(row, 2);
//        String yearVal = (String) musicTableModel.getValueAt(row, 3);
//
//        for (Music m : musicList) {
//            if (m.getTitle().equals(titleVal) &&
//                    m.getArtist().equals(artistVal) &&
//                    m.getAlbum().equals(albumVal) &&
//                    m.getYear().equals(yearVal)) {
//                m.setFavorite(!m.isFavorite());
//                musicTableModel.setValueAt(m.isFavorite() ? "❤️" : "🤍", row, 4);
//                break;
//            }
//        }
//    }
//
//
//    // 재생 기능
//    private void playSelected() {
//        int row = musicTable.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "재생할 음악을 선택하세요.");
//            return;
//        }
//        currentIndex = row;
//        String titleVal = (String) musicTableModel.getValueAt(row, 0);
//        String artistVal = (String) musicTableModel.getValueAt(row, 1);
//        JOptionPane.showMessageDialog(this, "재생 중: " + titleVal + " - " + artistVal);
//    }
//
//    private void playPrevious() {
//        if (currentIndex <= 0) {
//            JOptionPane.showMessageDialog(this, "이전 곡이 없습니다.");
//            return;
//        }
//        currentIndex--;
//        musicTable.setRowSelectionInterval(currentIndex, currentIndex);
//        playSelected();
//    }
//
//    private void playNext() {
//        if (currentIndex >= musicTable.getRowCount() - 1) {
//            JOptionPane.showMessageDialog(this, "다음 곡이 없습니다.");
//            return;
//        }
//        currentIndex++;
//        musicTable.setRowSelectionInterval(currentIndex, currentIndex);
//        playSelected();
//    }
//
//    // 삭제 기능
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int row = musicTable.getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "삭제할 음악을 선택하세요.");
//            return;
//        }
//        int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String titleVal = (String) musicTableModel.getValueAt(row, 0);
//            String artistVal = (String) musicTableModel.getValueAt(row, 1);
//            String albumVal = (String) musicTableModel.getValueAt(row, 2);
//            String yearVal = (String) musicTableModel.getValueAt(row, 3);
//
//            musicList.removeIf(m -> m.getTitle().equals(titleVal) &&
//                    m.getArtist().equals(artistVal) &&
//                    m.getAlbum().equals(albumVal) &&
//                    m.getYear().equals(yearVal));
//            musicTableModel.removeRow(row);
//        }
//    }
//
//    public static void main(String[] args) {
//        new PlayList01();
//    }
//}





