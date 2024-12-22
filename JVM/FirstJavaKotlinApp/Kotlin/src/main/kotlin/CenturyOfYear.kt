fun centuryOfYear(year: Int): Int {
    // 1 to 100 => 1
    // 101 to 200 => 2
    // 201 to 300 => 3
    // .....
    // 1001 to 1100 => 11
    // 1101 to 1200 => 12
    // 1201 to 1300 => 13
    // 1301 to 1400 => 14
    // 1401 to 1500 => 15
    // 1501 to 1600 => 16
    // 1601 to 1700 => 17
    // 1701 to 1800 => 18
    // 1801 to 1900 => 19
    // 1901 to 2000 => 20
    // 2001 to 2100 => 21
    var century = 0
    century =
        if (year % 100 == 0) {
            year / 100
        } else {
            (year / 100) + 1
        }

    return century
}