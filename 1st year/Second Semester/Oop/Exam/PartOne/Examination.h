#include <string>
#include <iostream>

class Examination {
private:
    std::string subject;
    int grade;

public:
    Examination(){};
    Examination(const std::string& givenSubject) : subject(givenSubject), grade(-1){};
    Examination(const std::string& givenSubject, int givenGrade) : subject(givenSubject), grade(givenGrade){};
    ~Examination(){};

    int getGrade() const;

    bool operator>(const Examination& e) const;
    bool operator>(int givenValue) const;
    friend std::ostream& operator<<(std::ostream& out, const Examination& e);

};

bool Examination::operator>(const Examination &e) const {
    if (this->grade > e.grade)
        return true;
    return false;
}

bool Examination::operator>(int givenValue) const {
    if (this->grade > givenValue)
        return true;
    return false;
}

inline std::ostream &operator<<(std::ostream &out, const Examination &e) {
    if (e.grade == -1) {
        out << "Student did not participate to the examination for discipline ";
        out << e.subject;
        out << "\n";
    }
    else {
        out << "To the examination for discipline ";
        out << e.subject;
        out << " the student obtained grade: ";
        out << std::to_string(e.grade);
        out << ".\n";
    }
    return out;
}

int Examination::getGrade() const {
    return this->grade;
}


