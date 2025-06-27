-- 데이터를 삽입할 데이터베이스 선택
USE benefitmoa;

-- policy 테이블에 초기 데이터 삽입
INSERT INTO `policy` (
    service_id, organization_name, organization_code, department_name, title,
    service_summary, support_content, target_audience, selection_criteria,
    support_type, application_period, application_method, reception_agency,
    contact, detail_url, user_type, category, policy_created_at,
    policy_updated_at, organization_type, view_count
)
VALUES
    ('TEST001', 'Ministry of Health', '1000001', 'Welfare Dept', 'Basic Pension',
     'Support for seniors', 'Monthly cash support', 'Age 65+', 'Income-based', 'Cash',
     'Always', 'Online, Visit', 'Local Office', '1234', 'https://example.com/policy/1', 'Individual', 'Welfare',
     '2022-01-01 10:00:00', '2024-01-01 09:00:00', 'Central Gov', 100),

    ('TEST002', 'Ministry of Labor', '1000002', 'Employment Dept', 'Job Program',
     'Help for job seekers', 'Training, coaching', 'Unemployed adults', 'Asset check', 'Service',
     'Always', 'Online', 'Employment Center', '5678', 'https://example.com/policy/2', 'Individual', 'Employment',
     '2022-01-02 10:00:00', '2024-01-02 09:00:00', 'Central Gov', 80),

    ('TEST003', 'Ministry of Housing', '1000003', 'Housing Dept', 'Rent Support',
     'Assist low-income renters', 'Rent subsidy', 'Low-income households', 'Income level', 'Cash',
     'Always', 'Visit', 'City Office', '1111', 'https://example.com/policy/3', 'Household', 'Housing',
     '2022-01-03 10:00:00', '2024-01-03 09:00:00', 'Central Gov', 95),

    ('TEST004', 'Youth Ministry', '1000004', 'Youth Programs', 'Youth Loan',
     'Loans for youth housing', 'Low-interest loan', 'Young adults', 'Age and income limit', 'Loan',
     'Annual', 'Online', 'Bank Partner', '2222', 'https://example.com/policy/4', 'Individual', 'Finance',
     '2022-01-04 10:00:00', '2024-01-04 09:00:00', 'Central Gov', 60),

    ('TEST005', 'Women Ministry', '1000005', 'Family Support', 'Child Allowance',
     'Monthly support for children', 'Cash for children', 'Families with children', 'Age under 18', 'Cash',
     'Always', 'Visit', 'Community Center', '3333', 'https://example.com/policy/5', 'Household', 'Welfare',
     '2022-01-05 10:00:00', '2024-01-05 09:00:00', 'Central Gov', 120),

    ('TEST006', 'Ministry of Veterans', '1000006', 'Veterans Dept', 'Veterans Support',
     'Aid for national veterans', 'Medical + Financial support', 'Registered veterans', 'Service history', 'Cash, Service',
     'Always', 'Online, Visit', 'Veterans Office', '4444', 'https://example.com/policy/6', 'Individual', 'Welfare',
     '2022-01-06 10:00:00', '2024-01-06 09:00:00', 'Central Gov', 50),

    ('TEST007', 'Ministry of Education', '1000007', 'Student Support', 'Scholarship',
     'Support for tuition', 'Full or partial tuition', 'College students', 'Grades, income', 'Cash',
     'Semester', 'Online', 'School Admin', '5555', 'https://example.com/policy/7', 'Individual', 'Education',
     '2022-01-07 10:00:00', '2024-01-07 09:00:00', 'Central Gov', 130),

    ('TEST008', 'Ministry of Culture', '1000008', 'Culture Fund', 'Art Grants',
     'Support for artists', 'Project funding', 'Professional artists', 'Portfolio review', 'Cash',
     'Quarterly', 'Online', 'Culture Agency', '6666', 'https://example.com/policy/8', 'Individual', 'Culture',
     '2022-01-08 10:00:00', '2024-01-08 09:00:00', 'Central Gov', 45),

    ('TEST009', 'Ministry of Technology', '1000009', 'Innovation Dept', 'Startup Fund',
     'Seed funding for startups', 'Grant and mentorship', 'Startup founders', 'Business plan review', 'Cash, Service',
     'Biannual', 'Online', 'Tech Office', '7777', 'https://example.com/policy/9', 'Individual', 'Startup',
     '2022-01-09 10:00:00', '2024-01-09 09:00:00', 'Central Gov', 150),

    ('TEST010', 'Ministry of Justice', '1000010', 'Legal Aid Dept', 'Legal Support',
     'Free legal help', 'Consultation & court aid', 'Low-income individuals', 'Income criteria', 'Service',
     'Always', 'Online, Visit', 'Legal Center', '8888', 'https://example.com/policy/10', 'Individual', 'Legal',
     '2022-01-10 10:00:00', '2024-01-10 09:00:00', 'Central Gov', 75),

    ('TEST011', 'Ministry of Agriculture', '1000011', 'Rural Dev Dept', 'Farm Support',
     'Support for farmers', 'Equipment + education', 'Registered farmers', 'Land ownership', 'Cash, Service',
     'Seasonal', 'Visit', 'Local Farm Office', '9999', 'https://example.com/policy/11', 'Household', 'Agriculture',
     '2022-01-11 10:00:00', '2024-01-11 09:00:00', 'Central Gov', 90),

    ('TEST012', 'Ministry of Defense', '1000012', 'Military Family Dept', 'Military Family Aid',
     'Aid for military families', 'Monthly support', 'Military dependents', 'Military status', 'Cash',
     'Always', 'Visit', 'Base Office', '1010', 'https://example.com/policy/12', 'Household', 'Welfare',
     '2022-01-12 10:00:00', '2024-01-12 09:00:00', 'Central Gov', 55),

    ('TEST013', 'Ministry of Transport', '1000013', 'Transit Aid Dept', 'Public Transit Discount',
     'Reduced fares for elderly', 'Transit card discount', 'Age 65+', 'Age verification', 'Service',
     'Always', 'Visit', 'Transit Center', '1112', 'https://example.com/policy/13', 'Individual', 'Transport',
     '2022-01-13 10:00:00', '2024-01-13 09:00:00', 'Central Gov', 200),

    ('TEST014', 'Ministry of Environment', '1000014', 'Green Fund', 'Energy Subsidy',
     'Eco-friendly home support', 'Install solar panels', 'Homeowners', 'Energy audit', 'Cash',
     'Annual', 'Online', 'Green Office', '1212', 'https://example.com/policy/14', 'Household', 'Environment',
     '2022-01-14 10:00:00', '2024-01-14 09:00:00', 'Central Gov', 65),

    ('TEST015', 'Ministry of Finance', '1000015', 'Tax Dept', 'Tax Credit for Families',
     'Tax deduction for dependents', 'Year-end tax credit', 'Families with dependents', 'Household size', 'Credit',
     'Yearly', 'Online', 'Tax Office', '1313', 'https://example.com/policy/15', 'Household', 'Finance',
     '2022-01-15 10:00:00', '2024-01-15 09:00:00', 'Central Gov', 85);


-- 주석 처리된 부분은 예시로 제공된 데이터 삽입 구문
# INSERT INTO `policy` (service_id, organization_name, organization_code, department_name, title, service_summary, support_content, target_audience, selection_criteria, support_type, application_period, application_method, reception_agency, contact, detail_url, user_type, category, policy_created_at, policy_updated_at, organization_type, view_count)
# VALUES
# ('R15254173926', '보건복지부', '1270000', '보건복지부 기초연금과', '기초연금', '어르신들의 편안한 노후생활을 도와드리고 연금 혜택을 공평하게 나누어 드리기 위함', '기준연금액과 국민연금 급여액 등을 고려하여 산정', '만 65세 이상 어르신', '소득인정액 기준', '현금', '상시', '방문, 온라인', '주소지 관할 읍·면·동 주민센터 또는 행정복지센터, 가까운 국민연금공단 지사 및 상담센터', '1355', 'https://www.bokjiro.go.kr/ssis-tbu/twataa/wlfareInfo/moveTWAT52011M.do?wlfareInfoId=WLF00000030', '개인', '생활안정', '2022-10-18 17:35:43.0', '2024-03-04 10:28:43.0', '중앙부처', 101083),
# ('R15254174005', '보건복지부', '1270000', '보건복지부 장애인연금과', '장애인연금', '장애인의 생활안정 지원', '장애정도, 연령 등을 고려하여 연금 지급', '만 18세 이상 등록 중증장애인', '소득인정액, 장애등급 기준', '현금', '상시', '방문, 온라인', '주소지 관할 읍·면·동 주민센터', '129', 'https://www.bokjiro.go.kr/ssis-tbu/twataa/wlfareInfo/moveTWAT52011M.do?wlfareInfoId=WLF00000032', '개인', '생활안정', '2022-10-18 17:35:43.0', '2024-03-04 10:29:13.0', '중앙부처', 54593),
# ('R15254174161', '고용노동부', '1352000', '고용노동부 국민취업지원부', '국민취업지원제도', '취업에 어려움을 겪는 국민에게 취업지원서비스와 생계지원을 함께 제공', '취업지원 프로그램, 구직활동비용 등 지원', '참여요건을 충족하는 미취업자', '소득, 재산, 취업경험 등 요건 심사', '서비스, 현금', '상시', '방문, 온라인', '거주지 관할 고용센터', '1350', 'https://www.work.go.kr/kua/index.do', '개인', '취업·창업', '2022-10-18 17:35:44.0', '2024-03-04 10:30:11.0', '중앙부처', 48434),
# ('R15254174246', '보건복지부', '1270000', '보건복지부 아동복지정책과', '아동수당', '아동 양육에 따른 경제적 부담 경감 및 건강한 성장 환경 조성', '매월 일정액의 수당 지급', '대한민국 국적을 보유한 아동', '연령, 국적 등 기준 충족', '현금', '상시', '방문, 온라인', '아동의 주민등록상 주소지 읍·면·동 주민센터', '129', 'https://www.bokjiro.go.kr/ssis-tbu/twataa/wlfareInfo/moveTWAT52011M.do?wlfareInfoId=WLF00000142', '가구', '생활안정', '2022-10-18 17:35:44.0', '2024-03-04 10:31:02.0', '중앙부처', 47164),
# ('R15254174526', '고용노동부', '1352000', '고용노동부 퇴직연금복지과', '퇴직연금', '근로자들의 노후 소득보장과 생활안정을 위함', '퇴직급여를 금융회사에 적립, 운용하여 근로자 퇴직 시 연금 또는 일시금으로 지급', '퇴직연금제도를 설정한 사업장의 근로자', '해당 사업장 재직', '현금', '해당없음', '해당없음', '해당없음', '1350', 'https://www.moel.go.kr/policy/policydata/view.do?bbs_seq=20210200473', '개인', '생활안정', '2022-10-18 17:35:45.0', '2024-03-04 10:32:01.0', '중앙부처', 38661),
# ('R15254174621', '보건복지부', '1270000', '보건복지부 자립지원과', '자립수당(보호종료아동)', '보호종료아동의 안정적인 사회 정착 지원', '매월 자립수당 지급', '아동복지시설 등에서 보호종료된 아동', '보호종료 후 5년 이내', '현금', '상시', '방문', '보호종료아동의 주민등록상 주소지 읍·면·동 주민센터', '129', 'https://www.bokjiro.go.kr/ssis-tbu/twataa/wlfareInfo/moveTWAT52011M.do?wlfareInfoId=WLF00000282', '개인', '생활안정', '2022-10-18 17:35:45.0', '2024-03-04 10:32:34.0', '중앙부처', 37728),
# ('R15254175027', '국토교통부', '1530000', '국토교통부 주거복지정책과', '주거급여', '저소득층의 주거안정과 주거수준 향상 도모', '임차료 보조, 주택 개량 지원 등', '소득인정액이 기준 중위소득 47% 이하인 가구', '소득, 재산 기준', '현금, 서비스', '상시', '방문, 온라인', '주소지 관할 읍·면·동 주민센터', '1577-0667', 'https://www.myhome.go.kr/hws/portal/cont/selectContHousingView.do#guide=MLTS00000038', '가구', '주거', '2022-10-18 17:35:46.0', '2024-03-04 10:33:48.0', '중앙부처', 33502);
