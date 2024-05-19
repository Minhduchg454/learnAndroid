package eu.tutorials.businesscard

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice (val name: String,val category: String) {
    var deviceStatus = "online"
        protected set

    open val deviceType="unknown"
    open fun turnOn(){
        deviceStatus = "on"
    }
    open fun turnOff(){
        deviceStatus = "off"
    }

    open fun printDeviceInfo(){
        println("Device name: $name, category: $category, type : $deviceType")
    }

}


class SmartTVdevice(deviceName: String, deviceCategory: String):
    SmartDevice(name=deviceName, category=deviceCategory){
    override val deviceType="Smart TV"
    private var spearkerVolume by RangeRegulator(intitialValue = 2, minValue = 0, maxValue = 100)

    private var chanelNumber by RangeRegulator(intitialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume(){
        spearkerVolume++
        println("Speaker volume increased to $spearkerVolume")
    }


    fun decreaseSpeakerVolume(){
        spearkerVolume--
        println("Speaker volume decreased to $spearkerVolume")
    }

    fun nextChanel(){
        chanelNumber++
        println("Chanel number increased to $chanelNumber")
    }

    fun previousChanel(){
        chanelNumber--
        println("Chanel number previous to $chanelNumber")
    }

    override fun turnOn(){
        super.turnOn()
        println("$name is turn on. Speaker volume is set to $spearkerVolume and channel number is "
                +"set to $chanelNumber")
    }

    override fun turnOff(){
        super.turnOff()
        println("$name is turn of")
    }

    override fun printDeviceInfo() {
        super.printDeviceInfo()
    }
}

class SmartLightDevice (deviceName: String, deviceCategory: String):
    SmartDevice(name=deviceName, category=deviceCategory){

        private var brightnessLevel by RangeRegulator(intitialValue = 0, minValue = 0, maxValue = 100)

        override val deviceType="Smart Light"
        fun increaseBrightness(){
            brightnessLevel++
            println("Brightness increased to $brightnessLevel")
        }

        fun decreaseBrightness(){
            brightnessLevel--
            println("Brightness decreased to $brightnessLevel")
        }

        override fun turnOn (){
            super.turnOn()
            brightnessLevel=2
            println("$name turn on. The brightness level is $brightnessLevel")
        }

        override fun turnOff(){
            super.turnOff()
            brightnessLevel=0
            println("Smart ligh is turn off")
        }

        override fun printDeviceInfo() {
            super.printDeviceInfo()
        }
    }

class SmartHome(
    val smartTvDevice: SmartTVdevice,
    val smartLightDevice: SmartLightDevice
){
    var deviceTurnOnCount=0
        private set
    fun turnOnTv(){
            deviceTurnOnCount++
            smartTvDevice.turnOn()
    }
    fun turnOffTv(){
            deviceTurnOnCount--
            smartTvDevice.turnOff()
    }

    fun decreaseTvVolume(){
        if (smartTvDevice.deviceStatus=="on") {
            smartTvDevice.decreaseSpeakerVolume()
        }else{
            println("Not action because device is off")
        }
    }

    fun increaseTvVolume(){
        if (smartTvDevice.deviceStatus=="on") {
            smartTvDevice.increaseSpeakerVolume()
        }else{
            println("Not action because device is off")
        }

    }

    fun changeTvChanelToNext(){
        if (smartTvDevice.deviceStatus=="on") {
            smartTvDevice.nextChanel()
        }else{
            println("Not action because device is off")
        }
    }

    fun changeTvChanelToPrevious(){
        if (smartTvDevice.deviceStatus=="on") {
            smartTvDevice.previousChanel()
        }else{
            println("Not action because device is off")
        }
    }

    fun printSmartTvInfo(){
        smartTvDevice.printDeviceInfo()
    }


    fun turnOnLight(){
            deviceTurnOnCount++
            smartLightDevice.turnOn()
    }

    fun turnOffLight(){
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness(){
        if(smartLightDevice.deviceStatus=="on"){
            smartLightDevice.increaseBrightness()
        }else{
            println("Not action because device is off")
        }
    }

    fun decreaseLightBrightness(){
        if(smartLightDevice.deviceStatus=="on"){
            smartLightDevice.decreaseBrightness()
        }else{
            println("Not action because device is off")
        }
    }


    fun printSmartLightInfo(){
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevice(){
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    intitialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
):ReadWriteProperty<Any?,Int>{

    var fieldData=intitialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if(value in minValue..maxValue){
            fieldData=value
        }
    }
}
fun main(){
    var smartDevice= SmartTVdevice("Android TV", "Entertainment")
    //smartDevice.turnOn()

    var smartDevice1= SmartLightDevice("Google light", "Utitly")
    //smartDevice1.turnOn()

    var Home1 = SmartHome(smartDevice,smartDevice1)
    Home1.turnOffTv()
    Home1.changeTvChanelToNext()




}
