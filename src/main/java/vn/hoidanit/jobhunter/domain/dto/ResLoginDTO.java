package vn.hoidanit.jobhunter.domain.dto;

// Do kiểm trả về của API login trả về một chuỗi JSON chứa access_token
// , nên cần tạo một class RestLoginDTO để chứa access_token
// để phù hợp FormatRestResponse.java
public class ResLoginDTO {
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
