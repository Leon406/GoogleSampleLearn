package demo.leon.me.dagger.dagger

import dagger.Module
import dagger.Provides
import demo.leon.me.dagger.MainActivity

/**
 * @author : Leon Shih
 * @time   : 2019/1/11 0011 19:51
 * @e-mail : deadogone@gmail.com    :
 * @desc   :
 */
@Module
class StudentModule(mainActivity: MainActivity) {

    @Provides
    fun provide() = Student()
}