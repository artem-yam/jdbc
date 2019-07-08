package com.epam.jdbc.command;

public enum CommandEnum {
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    ADMINPAGE {
        {
            this.command = new AdminPageCommand();
        }
    },
    USERCREATIONPAGE {
        {
            this.command = new UserCreationPageCommand();
        }
    },
    USERCREATIONCOMPLETED {
        {
            this.command = new UserCreationCompletedCommand();
        }
    },
    MAINPAGE {
        {
            this.command = new MainPageCommand();
        }
    };
    
    ActionCommand command;
    
    public ActionCommand getCurrentCommand() {
        return command;
    }
}