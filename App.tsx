import {
  Alert,
  Image,
  NativeModules,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import React, { useEffect, useState } from "react";

const App = () => {
  const { BatteryModule, NetworkModule, BrightnessModule } = NativeModules;
  const { CameraModule } = NativeModules;

  const [capturedImage, setCapturedImage] = useState(null);
  const [brightness, setBrightness] = useState(0.1);

  const fetchBatteryLevel = async () => {
    try {
      const level = await BatteryModule.getBatteryLevel();
      const cos = await BatteryModule.getConstants();
      const netInfo = await NetworkModule.getNetworkInfo();

      console.log("level", netInfo);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => {
    fetchBatteryLevel();
    getBrightness();
  }, []);

  const openNativeCamera = async () => {
    try {
      const result = await CameraModule.openCamera();

      setCapturedImage(result.uri);
    } catch (x) {
      console.log("resultresultresultresult", x);
    }
  };

  // const  {
  //   setBrightness: (brightness) => BrightnessModule.setBrightness(brightness),
  //   getBrightness: () => BrightnessModule.getBrightness(),
  // };

  const getBrightness = async () => {
    const bright = await BrightnessModule.getBrightness();
    console.log("brightnesss", bright);
  };
  return (
    <View
      style={{
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Image
        source={{ uri: `data:image/jpeg;base64,${capturedImage}` }}
        style={{ width: 200, height: 200, marginBottom: 20 }}
      />
      <TouchableOpacity onPress={openNativeCamera}>
        <Text>Open Camera</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={async () =>
          await BrightnessModule.setBrightness(brightness + 0.1)
        }
      >
        <Text>Increase Brightness</Text>
      </TouchableOpacity>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({});
