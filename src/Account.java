public class Account {
    private String password;
    private String username;
    private AccountStatus accountRole;

    public Account( String username) {
        this.username = username;
        this.accountRole= AccountStatus.USER;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean setPassword(String password) {
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;
        if(password == null || password.length() < 8){
            return false;
        }
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if(Character.isDigit(c)){
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
                
            }
        }
        if(hasDigit && hasSpecial && hasUpper){
            this.password = password;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }
}
