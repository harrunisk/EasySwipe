# EasySwipe
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License: MIT](https://img.shields.io/badge/License-MIT-silver.svg)](https://opensource.org/licenses/MIT)  
  
Android EasySwipe Library  
<p align="center">
<img src="https://github.com/harrunisk/EasySwipe/blob/master/art/EasySwipe.gif" >
</p>

#### Setup
1. Add the JitPack repository to your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. Add the LiquidSwipe dependency in the build.gradle:
```
implementation 'com.github.'
```
#### Usage
Normal Usage
```
    <nstudiosappdev.android.view.EasySwipe
        android:id="@+id/easySwipe"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
    </nstudiosappdev.android.view.EasySwipe>
```
Custom Usage
```
    <nstudiosappdev.android.view.EasySwipe
        android:id="@+id/easySwipeCustom"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/easySwipe3"
        app:acceptStartColor="@color/colorDefaultAcceptEnd"
        app:acceptCenterColor="@color/colorDefaultAcceptEnd"
        app:acceptEndColor="@color/colorDefaultAcceptEnd"
        app:rejectStartColor="@color/colorDefaultRejectEnd"
        app:rejectCenterColor="@color/colorDefaultRejectEnd"
        app:rejectEndColor="@color/colorDefaultRejectEnd"
        app:cornerRadius="90dp"
        app:thumb="@drawable/ic_pin_button">
    </nstudiosappdev.android.view.EasySwipe>
```
Don't forget to set listeners.
```
        easySwipe.setListener(object : EasySwipeListener {
            override fun onAccepted() {
                Toast.makeText(applicationContext, "Accepted!", Toast.LENGTH_SHORT).show()
            }

            override fun onRejected() {
                Toast.makeText(applicationContext, "Rejected!", Toast.LENGTH_SHORT).show()
            }
        })
```

### License
```
MIT License

Copyright (c) 2019 harrunisk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
