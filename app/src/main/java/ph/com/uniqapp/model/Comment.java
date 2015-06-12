package ph.com.uniqapp.model;

/**
 * Created by waelhe on 6/12/2015.
 */
public class Comment{
    private String event_id;
    private String content;
    private String user_id;


        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
}
