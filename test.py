from time import sleep
from selenium import webdriver
from selenium.webdriver.common.by import By

# なんと「pip install chromedriver-binary-auto」実行後以下をimport するだけでPATHが通る
import chromedriver_binary

# inputに対して入力するセッタを作成
def create_setter(id):
    def setter(value):
        print(id + "に" + value+"をセットします")
        return
    return setter

setter = create_setter("textInput")
setter("123456780")



import os
print(os.getcwd())

options = webdriver.ChromeOptions()
options.add_experimental_option('excludeSwitches', ['enable-logging'])#『Bluetooth: bluetooth_adapter_winrt.cc:1074 Getting Default Adapter failed』が目障りなので消す
#options.add_argument("--headless")# ヘッドレスで起動するオプションを指定
driver = webdriver.Chrome(options=options)
driver.get('file:///' + os.getcwd() + '/memorandum.html')

ids = driver.find_elements(By.TAG_NAME, "input")
for i in ids:
    print(i.get_attribute("id") + "種類は" + i.get_attribute("type"))

# テキストにはもちろん入力できる
textInput = driver.find_element(By.ID, "textInput")
textInput.send_keys("文字列テスト")
print("入力値:" + textInput.get_property("value"))

# type="number"に入力はできない
numberInput = driver.find_element(By.ID, "numberInput")
numberInput.send_keys("文字列テスト")
# numberInput.send_keys("777")# これはできる
print("入力値:" + numberInput.get_property("value"))

searchInput = driver.find_element(By.ID, "searchInput")
searchInput.send_keys("検索テスト")
print("入力値:" + searchInput.get_property("value"))

dateInput = driver.find_element(By.ID, "dateInput")
dateInput.send_keys("0019930430")# 日付の入力にはなぜか先頭に2桁パディングが必要な気がする
print("入力値:" + dateInput.get_property("value"))# 日付のフォーマットは「2022-10-15」

sleep(5)
driver.quit()
print("テストを自作サイトと同じリポジトリにいれてしまう。\n今は launch.json だが task.json にいれるのが良さそう")
exit(0)
# search_box = driver.find_element(By.NAME, "q")
# search_box.send_keys("競プロ")
# search_box.submit()
# sleep(2)

# テストケースクラス
# 「入力行動」と「出力確認を行う関数オブジェクト」のペア
class TestCase:
    def __init__(self, input_list, output):
        self.input_list = input_list
        self.output = output# 期待される出力と同じか確認を行う関数オブジェクト
    
    def execute(self):
        for action in self.input_list:
            action()
        return self.output()

    def execute_test(self):
        for action in self.input_list:
            action()
        if self.output() is True:
            print("OK")
        else:
            print("NG") 

# テストクラス
# 与えられたテストケースのリスト全てパスでテストをパスとする
class Test:
    def __init__(self, summary, test_case_list):
        self.summary = summary
        self.test_case_list = test_case_list
        self.test_pass_flag = True

    def execute_test(self):
        print(self.summary + "のテストを開始します")
        has_error = False

        for test_case in self.test_case_list:
            has_error = not test_case.execute()
            if has_error:
                self.test_pass_flag = False
                print("\tCASE : NG")
            else:
                print("\tCASE : PASS")

        if self.test_pass_flag:
            print(self.summary + "のテスト結果 : PASS")
            return True
        else:
            print(self.summary + "のテスト結果 : NG")
            return False

input_list = []
input_list.append(lambda :print(1))
input_list.append(lambda :print(2))

def output_function():
    return False
def output_function2():
    return True
output = output_function
test_case = TestCase(input_list, output)



test_case1 = TestCase(input_list, output_function)
test_case2 = TestCase(input_list, output_function2)
test_case3 = TestCase(input_list, output_function2)


test_case_list = []
test_case_list.append(test_case1)
test_case_list.append(test_case2)
test_case_list.append(test_case3)

test = Test("連番パディング", test_case_list)
test.execute_test()

test = Test("子番パディング", [test_case1, test_case2, test_case3])
test.execute_test()


def search_test_input():
    print("入力欄に入力します")
    search_box = driver.find_element(By.NAME, "q")
    search_box.send_keys("競プロ")

def search_test_output():
    search_box = driver.find_element(By.NAME, "q")
    value = search_box.get_property("value")
    print("q = " + value)
    ret = (value == "競プロ")
    return ret

# 次の目標は以下のようなテストケースをjsonから自動で生成すること
def search_test_input2():
    print("入力欄に入力します")
    search_box = driver.find_element(By.NAME, "q")
    search_box.send_keys("テスト")
def search_test_output2():
    search_box = driver.find_element(By.NAME, "q")
    value = search_box.get_property("value")
    print("q = " + value)
    ret = (value == "テスト2")
    return ret
test_case2 = TestCase([search_test_input2], search_test_output2)
# 次の目標は以上のようなテストケースをjsonから自動で生成すること


test_case = TestCase([search_test_input], search_test_output)#検索欄に入力した文字列が入力欄に入っているか(普通に考えたらTrue)
test = Test("検索欄に入力できるか", [test_case, test_case2])
result = test.execute_test()
print(result)

sleep(5)
driver.quit()