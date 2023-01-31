package main;

import java.io.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        // 글 번호 0~100
        for(int i = 0; i < 100; i++){

            try{
                String urlStr = "https://hyowonsarchive.tistory.com/" + i; // 글 url
                URL url = new URL(urlStr);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("post" + i +".html")); // 파일명

                String str = null;
                boolean savepoint = false; // 저장 시작/끝 분기처리를 위한 boolean

                while((str = bufferedReader.readLine()) != null) {

                    if(str.trim().equals("<div id=\"head\">")){ // 글 내용 시작시 뜨는 html tag
                        savepoint = true; // 저장 시작!
                    }
                    while(savepoint) {

                        System.out.println(str);
                        bufferedWriter.write(str);
                        bufferedWriter.newLine();
                        str = bufferedReader.readLine();
                        if(str.trim().equals("<div class=\"container_postbtn #post_button_group\">")){ // 글 내용 바로 밑에 뜨는 html tag
                            savepoint = false; // 저장 끝!
                        }
                    }

                }
                bufferedWriter.close();
                bufferedReader.close();

            } catch (FileNotFoundException e) {
                continue; // 없는 글일 때는 FileNotFoundException -> continue 해서 다음 번호로 진행
            } catch (Exception e) {
                break; // FileNotFoundException 이외의 오류는 프로그램 종료
            }



        }
    }
}
