# تثبيت Device Activity Tracker على Android 📱

## المتطلبات

- هاتف Android 8.0+
- Termux (تطبيق مجاني)
- اتصال إنترنت مستقر
- 500MB مساحة حرة

---

## الطريقة 1️⃣: استخدام Termux (الأسهل والأسرع)

### الخطوة 1: تثبيت Termux

1. اذهب إلى **F-Droid** أو **Google Play Store**
2. ابحث عن **Termux**
3. ثبّته (تطبيق مجاني)

> ⚠️ **هام**: استخدم F-Droid للحصول على أحدث إصدار

### الخطوة 2: إعداد البيئة

افتح Termux وشغّل:

```bash
# تحديث النظام
pkg update && pkg upgrade -y

# تثبيت Node.js و Git
pkg install nodejs-lts git curl -y

# التحقق من الإصدارات
node --version  # يجب أن يكون 20+
npm --version
```

### الخطوة 3: تثبيت التطبيق

```bash
# استنسخ المشروع
git clone https://github.com/gommzystudio/device-activity-tracker.git
cd device-activity-tracker

# ثبّت المكتبات (قد يستغرق 5-10 دقائق)
npm install
cd client && npm install && cd ..
```

### الخطوة 4: التشغيل

**للواجهة الويب:**
```bash
npm run start:server
```

ثم افتح متصفح وادخل: `http://localhost:3000`

**أو للـ CLI (سطر الأوامر):**
```bash
npm start
```

---

## الطريقة 2️⃣: من جهاز كمبيوتر عبر الشبكة المحلية

إذا كان لديك كمبيوتر وتريد الوصول من الهاتف:

### على جهاز الكمبيوتر:

```bash
# اذهب لمجلد المشروع
cd device-activity-tracker

# ثبّت المكتبات
npm install

# شغّل الخادم
npm run start:server
```

### على هاتف Android:

1. **تأكد من الاتصال بنفس شبكة الواي فاي**

2. **أوجد IP الكمبيوتر:**
   - **Windows**: افتح `cmd` واكتب `ipconfig` (ابحث عن IPv4)
   - **Mac/Linux**: اكتب `ifconfig` أو `hostname -I`
   - مثال: `192.168.1.100`

3. **في متصفح الهاتف، ادخل:**
   ```
   http://192.168.1.100:3000
   ```

---

## الطريقة 3️⃣: استخدام Docker (إن كان متاحاً)

```bash
# سحب الصورة
docker pull gommzystudio/device-activity-tracker

# تشغيل الحاوية
docker run -p 3000:3000 -p 3001:3001 \
  -e BACKEND_PORT=3001 \
  -e CLIENT_PORT=3000 \
  gommzystudio/device-activity-tracker
```

---

## خطوات الاستخدام

### 1️⃣ المسح الأول (QR Code)

```bash
npm start
```

سيظهر رمز QR - امسحه بـ WhatsApp من جهاز آخر

### 2️⃣ إدخال رقم الهدف

أدخل رقم الهاتف المراد تتبعه:
```
أدخل رقم الهاتف: +966501234567
```

### 3️⃣ مراقبة الحالة

ستظهر النتائج:
```
🟢 Online   - الجهاز قيد الاستخدام
🟡 Standby  - الجهاز في وضع السكون
🔴 Offline  - الجهاز غير متصل
```

---

## 🔧 استكشاف الأخطاء الشائعة

### ❌ مشكلة: "npm not found"
```bash
pkg install nodejs -y
```

### ❌ مشكلة: "Port already in use"
```bash
# استخدم منافذ مختلفة
export BACKEND_PORT=3002
export CLIENT_PORT=3001
npm run start:server
```

### ❌ مشكلة: "auth_info_baileys not found"
```bash
# سيُنشأ تلقائياً عند المسح الأول
rm -rf auth_info_baileys/
npm start
```

### ❌ مشكلة: "الاتصال ضعيف"
```bash
# استخدم الوضع عالي الإصرار
npm start -- --debug
```

### ❌ مشكلة: "Failed to connect to WhatsApp"
1. احذف مجلد `auth_info_baileys`
2. أعد تشغيل التطبيق
3. امسح رمز QR جديد

### ❌ مشكلة: "لا يعمل في الخلفية"
- استخدم Termux Session للعمل في الخلفية
- أو ثبّت تطبيق Tasker للأتمتة

---

## 📊 ميزات التطبيق

✅ **WhatsApp Tracking**
- تتبع حالة الجهاز (Online/Standby/Offline)
- قياس سرعة الاستجابة (RTT)
- عرض النتائج في الوقت الفعلي

✅ **Signal Support**
- نفس الميزات لتطبيق Signal
- يحتاج `signal-cli-rest-api`

✅ **واجهة الويب**
- عرض الرسوم البيانية
- تبديل طرق المسح
- عرض السجل التاريخي

---

## ⚡ نصائح لتحسين الأداء

1. **استخدم Termux Session:**
   ```bash
   # اضغط Ctrl+D لفصل Session
   # اضغط Volume Down + D لعرض الـ Overlay
   ```

2. **استخدم tmux للعمل في الخلفية:**
   ```bash
   pkg install tmux
   tmux new-session -d -s tracker
   tmux send-keys -t tracker "npm run start:server" Enter
   ```

3. **حفظ البطارية:**
   - قلل تكرار المسح (في الكود)
   - استخدم الشاشة المنخفضة
   - أغلق التطبيقات الأخرى

---

## 📚 المراجع المفيدة

- [Termux Wiki](https://wiki.termux.com)
- [Node.js on Termux](https://wiki.termux.com/wiki/Node.js)
- [WhatsApp Baileys](https://github.com/WhiskeySockets/Baileys)
- [المستودع الأصلي](https://github.com/gommzystudio/device-activity-tracker)

---

## ⚠️ تنبيهات أمان وقانون مهمة

🔴 **لا تفعل:**
- تتبع الأشخاص بدون موافقة صريحة
- مشاركة بيانات المصادقة
- فتح المنافذ للإنترنت العام
- استخدام التطبيق لأغراض غير قانونية

🟢 **افعل:**
- استخدم VPN محلي فقط
- احفظ `auth_info_baileys` بأمان
- اقرأ القوانين المحلية قبل الاستخدام
- احذر من استهلاك البطارية

---

## 🚀 الخطوات التالية

1. **تثبيت Termux** (إن لم تكن مثبتاً)
2. **تشغيل الأوامر أعلاه**
3. **مسح رمز QR**
4. **إدخال رقم الهدف**
5. **مراقبة النتائج**

---

**آخر تحديث**: يناير 2026
**الإصدار**: 1.0.0
**الدعم**: https://github.com/gommzystudio/device-activity-tracker/issues
