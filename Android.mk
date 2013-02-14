LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v13
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := GrabFocus

#LOCAL_SDK_VERSION := current

LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)

# Use the following include to make our test apk.
#include $(call all-makefiles-under,$(LOCAL_PATH))

##################################################
include $(CLEAR_VARS)

#LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := android-query:libs/android-query-0.21.7.jar

include $(BUILD_MULTI_PREBUILT)
