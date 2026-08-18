import segno

qrcode = segno.make_qr("Hello World")
qrcode.save('Hello_qrcode.png',
            scale=5,
            border=10,)

