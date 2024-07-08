package com.hcl.ownerMicroservice.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.ownerMicroservice.model.Owner;
import com.hcl.ownerMicroservice.repository.OwnerRepository;

@Service
public class OwnerDaoService {
	private final OwnerRepository ownerRepository;

	public OwnerDaoService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	public boolean isExistById(int id) {
		final Optional<Owner> optionalOwner = ownerRepository.findById(id);
		return optionalOwner.isPresent();
	}

	public List<Owner> getAll() {
		return ownerRepository.findAll();
	}

	public Optional<Owner> getOneById(int id) {
		return ownerRepository.findById(id);
	}

	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}

	public boolean delete(int id) {
		ownerRepository.deleteById(id);
		return true;
	}

}
