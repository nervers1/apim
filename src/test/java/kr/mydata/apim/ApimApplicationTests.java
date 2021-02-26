package kr.mydata.apim;

class ApimApplicationTests {
    public static void main(String[] args) {
        String header = "Bearer ASDF";
        int index = header.indexOf("Bearer");

        System.out.println(index);

        if (index > 0) {

        }

    }

    void contextLoads() {
    }

}
