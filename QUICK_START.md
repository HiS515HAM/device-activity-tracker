# البدء السريع - Device Activity Tracker ⚡

## تثبيت سريع (5 دقائق)

### على Termux (Android):

```bash
# 1. تحديث النظام
pkg update && pkg upgrade -y

# 2. تثبيت المتطلبات
pkg install nodejs-lts git -y

# 3. نسخ المشروع
git clone https://github.com/gommzystudio/device-activity-tracker.git
cd device-activity-tracker

# 4. التثبيت
npm install
cd client && npm install && cd ..

# 5. التشغيل
npm start
```

### على الكمبيوتر:

```bash
# تثبيت Node.js 20+
# ثم:
git clone https://github.com/gommzystudio/device-activity-tracker.git
cd device-activity-tracker
npm install
npm run start:server
# افتح: http://localhost:3000
```

---

## خطوات الاستخدام

1. **المسح الأول**
   ```bash
   npm start
   ```
   سيظهر رمز QR

2. **امسح الرمز بـ WhatsApp**
   - اذهب لـ WhatsApp → الإعدادات → الأجهزة المرتبطة
   - اضغط على ربط جهاز
   - امسح الرمز

3. **أدخل رقم الهدف**
   ```
   أدخل رقم الهاتف: +966501234567
   ```

4. **مراقبة النتائج**
   - 🟢 Online = قيد الاستخدام
   - 🟡 Standby = في السكون
   - 🔴 Offline = غير متصل

---

## المشاكل الشائعة وحلولها

| المشكلة | الحل |
|--------|------|
| npm not found | `pkg install nodejs -y` |
| Port in use | `export BACKEND_PORT=3002` |
| QR Code error | `rm -rf auth_info_baileys/` |
| Slow performance | قلل تكرار المسح |

---

## الروابط المهمة

- 📖 [دليل التثبيت الكامل](ANDROID_SETUP.md)
- 🔧 [استكشاف الأخطاء](ANDROID_SETUP.md#-استكشاف-الأخطاء-الشائعة)
- ⚠️ [تحذيرات الأمان](ANDROID_SETUP.md#-تنبيهات-أمان-وقانون-مهمة)
- 🌐 [المستودع الأصلي](https://github.com/gommzystudio/device-activity-tracker)

---

**استعد للبدء الآن!** 🚀
