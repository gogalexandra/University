#include <vector>

template<typename T>
class fct {
private:
    std::vector<T> db;

public:
    int fct(std::vector<T>& givenVector) : db(givenVector){
        return std::max(this->db);
    };



};
