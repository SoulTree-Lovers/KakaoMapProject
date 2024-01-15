package kakao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import kakao.util.KakaoAPI;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("주소를 입력해주세요: ");
            String address = reader.readLine();

            double[] coordinates = KakaoAPI.getAddressCoordinate(address);

            if (coordinates != null) {
                System.out.println("주소: " + address);
                System.out.println("위도: " + coordinates[0]);
                System.out.println("경도: " + coordinates[1]);
            } else {
                System.out.println("주소를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
