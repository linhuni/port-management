public class Account {
    private String password;
    private String username;
    private AccountRole accountRole;
    private AccountStatus accountStatus;

    // Constructor for sign in
    public Account(String username) {
        this.username = username;
        this.accountStatus = AccountStatus.PENDING;
        this.accountRole = AccountRole.MANAGER; // hoặc USER tuỳ đề
    }

    public Account(){};

    // Constructor for  predefined account (ADMIN/MANAGER)
    public Account(String username, String password, AccountRole role) {
        this.username = username;
        this.accountRole = role;
        this.accountStatus = AccountStatus.PENDING;
        //  setPassword for validating + setting ACTIVE
        if (!setPassword(password)) {
            throw new IllegalArgumentException("Invalid predefined password for user: " + username);
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean setPassword(String password) {
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;

        if (password == null || password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        if (hasDigit && hasSpecial && hasUpper) {
            this.password = password;
            this.accountStatus = AccountStatus.ACTIVE;
            return true;
        }
        return false;
    }


    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public String getUsername() {
        return username;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", accountRole=" + accountRole +
                ", accountStatus=" + accountStatus +
                '}';
    }

    public String getPassword() {
        return password;
    }
}
