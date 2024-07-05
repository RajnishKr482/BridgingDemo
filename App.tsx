import { NativeModules, StyleSheet, Text, View } from "react-native";
import React, { useEffect } from "react";

const App = () => {
  const { BatteryModule, NetworkInfoModule } = NativeModules;

  const fetchBatteryLevel = async () => {
    try {
      const level = await BatteryModule.getBatteryLevel();
      const cos = await BatteryModule.getConstants();
      const netInfo = await NetworkInfoModule.getNetworkInfo();

      console.log("level", netInfo);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => {
    fetchBatteryLevel();
  }, []);

  return (
    <View>
      <Text>App</Text>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({});
