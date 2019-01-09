package edu.csu2017fa314.T30.Model;

public class ServerRequest {
    private String query = "";
    private String id = "";

    public ServerRequest(String name, String id) {
        this.query = query;
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String name) {
        this.query = query;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
