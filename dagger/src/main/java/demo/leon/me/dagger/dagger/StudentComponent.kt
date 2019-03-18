package demo.leon.me.dagger.dagger

import dagger.Component
import demo.leon.me.dagger.MainActivity

/**
 * @author : Leon Shih
 * @time   : 2019/1/11 0011 19:55
 * @e-mail : deadogone@gmail.com    :
 * @desc   :
 */
@Component(modules = [StudentModule::class])
interface StudentComponent {
    fun inject(mainActivity: MainActivity)
}