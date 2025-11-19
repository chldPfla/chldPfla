package playlist;

public class Music {
    private String title;
    private String artist;
    private String album;
    private String year;
    private boolean favorite;
    private String filePath;  // mp3 파일 경로

    public Music(String title, String artist, String album, String year, String filePath) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.filePath = filePath;
        this.favorite = false;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public String getYear() { return year; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public String getFilePath() { return filePath; }
}
