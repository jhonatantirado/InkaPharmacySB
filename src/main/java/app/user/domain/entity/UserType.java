package app.user.domain.entity;

public enum UserType {
    OWNER{
    public String toString() {
            return "OWNER";
        }
    }, 
    SUPERVISOR{
    public String toString() {
            return "SUPERVISOR";
        }
    }, 
    ASSISTANT{
    public String toString() {
            return "ASSISTANT";
        }
    }
}
