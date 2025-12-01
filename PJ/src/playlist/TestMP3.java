package playlist;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class TestMP3 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\dw-009\\Desktop\\Lost.mp3");
            Player player = new Player(fis);
            System.out.println("재생 시작");
            player.play(); // 끝까지 재생
            System.out.println("재생 종료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}