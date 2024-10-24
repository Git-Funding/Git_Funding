<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../css/wonjin_reward_detail.css">
</head>
<body>
	<div class="PageWrapper">
		<section class="LeftContainer">
		</section>
		<section class="RightContainer">
			<section class="RightTop">
			</section>
			<section class="RightBottom">
				<div class="RightBottomTopContainer">
					<h2>리워드 목록</h2>
					<button @click="NavToRewardInsert()">리워드 추가</button>
				</div>
				<div class="RewardPrintContainer" v-for="vo in reward_detail_list">
					<div class="Reward1st">
						<div class="Reward1stTop">
							<p>가격 : {{vo.price}}</p>
								<div class="Reward1stTopBtnBox">
									<button class="RewardUpBtn" @click="RewardUp(vo.rno)">수정</button>
									<button class="RewardDelBtn" @click="RewardDel(vo.rno)">삭제</button>
								</div>
						</div>
						<br><p>남은 개수 : {{vo.amount}}</p>
						<br><p>리워드 명 : {{vo.name}}</p>
					</div>
					<div class="Reward2nd">
						<span>리워드 구성 : {{vo.description}}</span>
					</div>
					<div class="Reward3rd">
						<span>배송비 : {{vo.delivery}}</span>
					</div>
					<div class="Reward4th">
						<span>발송 시작일 : {{vo.del_start}}</span>
					</div>
					<div class="Reward5th">
						<span>총 개수 : {{vo.limit}}</span>
					</div>
				</div>
			</section>
		</section>
	</div>
	<script>
		let detail_listApp = Vue.createApp({
			data() {
				return {
					fno : '${fno}',
					reward_detail_list : []
				}
			},
			mounted() {
				this.dataRecv()
			},
			methods: {
				dataRecv() {
					axios.get('../project/reward_detail_vue.do', {
						params : {
							fno : this.fno
						}
					}).then(response => {
						console.log(response.data)
						
						this.reward_detail_list = response.data.list
					}).catch(error => {
						console.log(error.response)
					})
				},
				NavToRewardInsert() {
					location.href = '../project/reward_insert.do'
				},
				RewardUp() {
					
				},
				RewardDel() {
					axios.get('../project/reward_delete_vue.do', {
						params : {
							fno : this.fno
							rno : rno
						}
					}).then(respones => {
						if (response.data === 'yes') {
							location.href = '../project/reward_detail_vue.do'
						} else {
							alert('삭제 실패')
							console.log(response.data)
						}
					}).catch(error => {
						console.log(error.response)
					})
				}
			}
		}).mount('.PageWrapper')
	</script>
</body>
</html>