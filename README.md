# 课程管理

Intent intent = new Intent();
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setAction(Intent.ACTION_VIEW);
intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory()+"/1.txt"), "text/plain");
startActivity(intent);

//打开Word
Intent intent = new Intent("android.intent.action.VIEW");
intent.addCategory("android.intent.category.DEFAULT");
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/msword");

//打开文本
Intent intent = new Intent("android.intent.action.VIEW");
intent.addCategory("android.intent.category.DEFAULT");
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri1, "text/plain");

//打开PDF
Intent intent = new Intent("android.intent.action.VIEW");
intent.addCategory("android.intent.category.DEFAULT");
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "application/pdf");

//打开图片
Intent intent = new Intent("android.intent.action.VIEW");
intent.addCategory("android.intent.category.DEFAULT");
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
intent.setDataAndType(uri, "image/*");







