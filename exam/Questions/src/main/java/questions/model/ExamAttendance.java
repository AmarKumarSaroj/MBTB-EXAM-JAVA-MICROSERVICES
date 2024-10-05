package questions.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblexamattendance")
public class ExamAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chrexamattendancecode", insertable = false, updatable = false)
    private String chrexamattendancecode;

    private String username;
    private Date dttAttemptDate;
    private String chrAttendance;
    private Date dttSubmitDate;

    // Private constructor to enforce use of the builder
    private ExamAttendance(Builder builder) {
        this.chrexamattendancecode = builder.chrexamattendancecode;
        this.username = builder.username;
        this.dttAttemptDate = builder.dttAttemptDate;
        this.chrAttendance = builder.chrAttendance;
        this.dttSubmitDate = builder.dttSubmitDate;
    }

    // Getters

    public String getChrexamattendancecode() {
        return chrexamattendancecode;
    }

    public String getUsername() {
        return username;
    }

    public Date getDttAttemptDate() {
        return dttAttemptDate;
    }

    public String getChrAttendance() {
        return chrAttendance;
    }

    public Date getDttSubmitDate() {
        return dttSubmitDate;
    }

    @Override
    public String toString() {
        return "ExamAttendance [chrexamattendancecode=" + chrexamattendancecode + ", username=" + username
                + ", dttAttemptDate=" + dttAttemptDate + ", chrAttendance=" + chrAttendance + ", dttSubmitDate="
                + dttSubmitDate + "]";
    }

    // Builder class
    public static class Builder {
        private String chrexamattendancecode;
        private String username;
        private Date dttAttemptDate;
        private String chrAttendance;
        private Date dttSubmitDate;

        public Builder setChrexamattendancecode(String chrexamattendancecode) {
            this.chrexamattendancecode = chrexamattendancecode;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setDttAttemptDate(Date dttAttemptDate) {
            this.dttAttemptDate = dttAttemptDate;
            return this;
        }

        public Builder setChrAttendance(String chrAttendance) {
            this.chrAttendance = chrAttendance;
            return this;
        }

        public Builder setDttSubmitDate(Date dttSubmitDate) {
            this.dttSubmitDate = dttSubmitDate;
            return this;
        }

        public ExamAttendance build() {
            return new ExamAttendance(this);
        }
    }
}
