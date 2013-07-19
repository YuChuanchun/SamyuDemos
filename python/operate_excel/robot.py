from xlwt import Workbook
from xlrd import open_workbook
from xlutils.save import save

read = open_workbook("yuchuanchun.xlsx")
sheet = read.sheet_by_name("weekly_report")
print sheet.nrows

book = Workbook()