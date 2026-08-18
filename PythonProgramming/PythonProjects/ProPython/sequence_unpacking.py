# 'propython.com'.split('.')
# # ['propython', 'com']
# components = 'propython.com'.split('.')
# print(components)
#
# domain, tld = 'propython.com'.split('.')
# print(domain)
# print(tld)
#
# domain, tld = 'www.propython.com'.split('.')
# print(domain)

domain, *path = 'propython.com/example/url'.split('/')
print(domain)
print(path)