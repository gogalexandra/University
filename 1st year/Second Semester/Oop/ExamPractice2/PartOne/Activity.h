#pragma once

#include <string>
#include <iostream>

class Activity {
private:
    std::string title;
    std::string hour;

public:
    Activity();
    Activity(const std::string& givenTitle, const std::string& givenHour);
    ~Activity();

    std::string getTitle();
    std::string getHour();

    friend std::ostream& operator<<(std::ostream& out, const Activity& a);

};

inline std::ostream& operator<<(std::ostream& out, const Activity& a) {
    out << "Activity " << a.title << " will take place at " << a.hour << ".";
    return out;
}

Activity::Activity() {

}

Activity::Activity(const std::string& givenTitle, const std::string& givenHour) {
    this->title = givenTitle;
    this->hour = givenHour;
}

Activity::~Activity() {

}

std::string Activity::getTitle() {
    return this->title;
}

std::string Activity::getHour() {
    return this->hour;
}


