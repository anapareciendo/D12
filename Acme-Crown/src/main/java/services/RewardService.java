package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RewardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Crown;
import domain.Project;
import domain.Reward;

@Service
@Transactional
public class RewardService {

	//Managed repository
	@Autowired
	private RewardRepository	rewardRepository;


	//Validator
//	@Autowired
//	private Validator validator;
	
	//Supporting services

	//Constructors
	public RewardService() {
		super();
	}

	//Simple CRUD methods
	public Reward create(Project project) {
		Reward res;
		res = new Reward();
		res.setProject(project);
		res.setCrowns(new ArrayList<Crown>());
		return res;
	}

	public Collection<Reward> findAll() {
		final Collection<Reward> res = this.rewardRepository.findAll();
		return res;
	}

	public Reward findOne(final int rewardId) {
		final Reward res = this.rewardRepository.findOne(rewardId);
		return res;
	}

	public Reward save(final Reward reward) {
		Assert.notNull(reward, "The reward to save cannot be null.");
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		final Authority a = new Authority();
		a.setAuthority(Authority.CROWN);
		Assert.isTrue(ua.getAuthorities().contains(a), "You must to be a crown to create a reward.");
		
		Assert.isTrue(reward.getProject().getCrown().getUserAccount().equals(ua), "You are not the owner of the project");
		
		final Reward res = this.rewardRepository.save(reward);
		return res;
	}

	public void delete(final Reward reward) {
		final UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		final Authority a = new Authority();
		a.setAuthority(Authority.CUSTOMER);
		Assert.isTrue(ua.getAuthorities().contains(a), "You must to be a customer to delete a reward.");

		Assert.notNull(reward, "The reward to delete cannot be null.");
		Assert.isTrue(this.rewardRepository.exists(reward.getId()));
		
		Assert.isTrue(reward.getProject().getCrown().getUserAccount().equals(ua), "You are not the owner of the project");

		Assert.isNull(reward.getCrowns().isEmpty(), "The reward cannot be delete with Crowns");
		
		this.rewardRepository.delete(reward);
	}

	//Utilites methods

}