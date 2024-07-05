import {NativeModules, StyleSheet, Text, View} from 'react-native';
import React, {useEffect} from 'react';

const App = () => {
  const {BatteryModule} = NativeModules;

  const fetchBatteryLevel = async () => {
    try {
      const level = await BatteryModule.getBatteryLevel();
      const cos = await BatteryModule.getConstants();

      console.log('level', level);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => {
    fetchBatteryLevel();
  }, []);

  console.log('BatteryModule', BatteryModule);

  return (
    <View>
      <Text>App</Text>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({});
