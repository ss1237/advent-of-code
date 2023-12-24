#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int id = 1, total = 0;

    while (line != "") {
        line += " ";
        int beg = 0, end = line.find(" ");
        bool valid = true;
        string last_num = "";
        while (end != -1) {
            string cur = line.substr(beg, end - beg);
            if (cur[0] >= 'a' && cur[0] <= 'z') {
                if (cur[0] == 'r' && stoi(last_num) > 12) valid = false;
                if (cur[0] == 'g' && stoi(last_num) > 13) valid = false;
                if (cur[0] == 'b' && stoi(last_num) > 14) valid = false;
            }
            else {
                last_num = cur;
            }
            beg = end + 1;
            end = line.find(" ", end + 1);
        }
        if (valid) total += id;
        id++;
        getline(input, line);
    }
    
    cout << total << endl;
}
