package pl.uz.domian;

import com.wf.captcha.SpecCaptcha;

public class Captcha extends SpecCaptcha {

    String usersCode;

    public Captcha(int i, int i1) {
        super(i, i1);
    }

    public String getUsersCode() {
        return usersCode;
    }

    public void setUsersCode(String usersCode) {
        this.usersCode = usersCode;
    }
}
