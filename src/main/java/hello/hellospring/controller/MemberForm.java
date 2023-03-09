package hello.hellospring.controller;

public class MemberForm {
    private String name; //html의 name과 이름을 같게 만들어야 함.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
