package motobeans.architecture.development.interfaces

/**
 * Created by swati on 24/9/2018.
 */

interface EndPoint {

    /** The base API URL. */
    val url: String?

    /** A name for differentiating multiple API URLs */
    val name: String?
}