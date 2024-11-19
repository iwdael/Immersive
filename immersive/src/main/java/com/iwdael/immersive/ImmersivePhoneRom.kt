package com.iwdael.immersive

import com.iwdael.immersive.rom.*

private val registeredRom = arrayListOf<PhoneRom>(
    GooglePhoneRom(),
    HuaweiPhoneRom(),
    OnePlusPhoneRom(),
    OppoPbemPhoneRom(),
    OppoPhoneRom(),
    SamsungPhoneRom(),
    VivoPhoneRom(),
    XiaoMiPhoneRom(),
    BlackSharkPhoneRom()
)

object Immersive {
    @JvmStatic
    fun registerPhoneRom(rom: PhoneRom) {
        registeredRom.add(0, rom)
    }

    @JvmStatic
    fun getCurrentPhoneRom(): PhoneRom {
        registeredRom.forEach {
            if (it.isCurrentPhoneRom()) return it
        }
        return defaultPhoneRom
    }

    val defaultPhoneRom by lazy { DefaultPhoneRom() }
}

val currentPhoneRom get() = Immersive.getCurrentPhoneRom()