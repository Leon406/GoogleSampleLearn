package demo.leon.me.dagger.dagger

import javax.inject.Inject

/**
 * @author : Leon Shih
 * @time   : 2019/1/11 0011 19:50
 * @e-mail : deadogone@gmail.com    :
 * @desc   :
 */


class Student(val name: String) {
    @Inject
    constructor() : this("Leon")
}